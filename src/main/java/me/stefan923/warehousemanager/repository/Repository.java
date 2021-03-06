package me.stefan923.warehousemanager.repository;

import me.stefan923.warehousemanager.connection.ConnectionFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class Repository<T> {

     protected static final Logger LOGGER = Logger.getLogger(Repository.class.getName());

     protected final Class<T> type;

     public Repository(Class<T> type) {
          this.type = type;
     }

     /**
      * Returns the data associated with a class type (T).
      *
      * @return a list of elements of type T.
      */
     public List<T> findAll() {
          String query = SQLStatements.SELECT_ALL.replace("%table%", type.getSimpleName());

          ResultSet resultSet = null;
          try (Connection connection = ConnectionFactory.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(query)) {

               resultSet = preparedStatement.executeQuery();

               return createObjects(resultSet);
          } catch (SQLException e) {
               e.printStackTrace();
          } finally {
               if (resultSet != null) {
                    ConnectionFactory.close(resultSet);
               }
          }

          return Collections.emptyList();
     }

     /**
      * Returns the data associated with a class type (T) and an id.
      *
      * @param id - the identifier of the element whose data is to be retrieved.
      * @return an object of type Optional<T>.
      */
     public Optional<T> findById(int id) {
          String query = SQLStatements.SELECT_BY_FIELD
                  .replace("%table%", type.getSimpleName())
                  .replace("%field%", "id");

          ResultSet resultSet = null;
          try (Connection connection = ConnectionFactory.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(query)) {

               preparedStatement.setInt(1, id);
               resultSet = preparedStatement.executeQuery();

               return Optional.of(createObjects(resultSet).get(0));
          } catch (SQLException e) {
               LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:findById " + e.getMessage());
          } finally {
               if (resultSet != null) {
                    ConnectionFactory.close(resultSet);
               }
          }

          return Optional.empty();
     }

     /**
      * Saves the data associated with a class type (T). This method attempts to save an object.
      *
      * @param t - the object whose data is to be saved.
      * @return the object (as Optional<T>), if object's data could be saved, empty Optional<t> otherwise.
      */
     public T insert(T t) {
          String query = SQLStatements.INSERT
                  .replace("%table%", type.getSimpleName())
                  .replace("%fields%", getFieldsAsString(t))
                  .replace("%field_values%", getFieldValuesAsString(t));

          try (Connection connection = ConnectionFactory.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(query)) {

               preparedStatement.execute();
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return t;
     }

     /**
      * Saves the data associated with a class type (T). This method attempts to save an object that already exists
      * into the database.
      *
      * @param t - the object whose data is to be saved.
      * @return the object (as Optional<T>), if object's data could be saved, empty Optional<t> otherwise.
      */
     public T update(T t) {
          String query = SQLStatements.UPDATE_BY_FIELD
                  .replace("%table%", type.getSimpleName())
                  .replace("%fields%", getFieldsAndTheirValuesAsString(t, "id"))
                  .replace("%field%", "id");

          try ( Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query) ) {

               preparedStatement.setString(1, getFieldValueAsString(t, "id"));
               preparedStatement.executeUpdate();
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return t;
     }

     /**
      * Deletes the data associated with a class type (T) and an id.
      *
      * @param id - the identifier of the object whose data is to be deleted.
      * @return true if delete was successfull, false otherwise.
      */
     public boolean delete(int id) {
          String query = SQLStatements.DELETE_BY_FIELD
                  .replace("%table%", type.getSimpleName())
                  .replace("%field%", "id");

          try ( Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query) ) {

               preparedStatement.setInt(1, id);
               return preparedStatement.executeUpdate() > 0;
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return false;
     }

     protected List<T> createObjects(ResultSet resultSet) {
          List<T> createdObjects = new ArrayList<>();
          Constructor<?>[] constructors = type.getDeclaredConstructors();
          Constructor<?> constructor = null;

          for (Constructor<?> item : constructors) {
               constructor = item;
               if (constructor.getGenericParameterTypes().length == 0) {
                    break;
               }
          }

          try {
               while (resultSet.next()) {
                    constructor.setAccessible(true);
                    T instance = (T) constructor.newInstance();
                    for (Field field : type.getDeclaredFields()) {
                         String fieldName = field.getName();
                         Object value = resultSet.getObject(fieldName);
                         PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                         Method method = propertyDescriptor.getWriteMethod();
                         method.invoke(instance, value);
                    }
                    createdObjects.add(instance);
               }
          } catch (Exception e) {
               e.printStackTrace();
          }

          return createdObjects;
     }

     private String getFieldsAsString(T t) {
          return Arrays.stream(t.getClass().getDeclaredFields())
                  .map(Field::getName).collect(Collectors.joining(", "));
     }

     private String getFieldValuesAsString(T t) {
          return Arrays.stream(type.getDeclaredFields())
                  .map(field -> {
                       try {
                            field.setAccessible(true);
                            return String.valueOf(field.get(t));
                       } catch (IllegalAccessException e) {
                            e.printStackTrace();
                       }
                       return "";
                  }).collect(Collectors.joining("', '", "'", "'"));
     }

     private String getFieldValueAsString(T t, String fieldName) {
          Optional<Field> optionalField = Arrays.stream(type.getDeclaredFields())
                  .filter(tempField -> tempField.getName().equalsIgnoreCase(fieldName))
                  .findFirst();

          try {
               if (optionalField.isPresent()) {
                    Field field = optionalField.get();
                    field.setAccessible(true);
                    return String.valueOf(field.get(t));
               }
          } catch (IllegalAccessException e) {
               e.printStackTrace();
          }

          return "";
     }

     private String getFieldsAndTheirValuesAsString(T t, String... ignoredFields) {
          return Arrays.stream(type.getDeclaredFields())
                  .filter(field -> Arrays.stream(ignoredFields)
                          .noneMatch(ignoredField -> ignoredField.equalsIgnoreCase(field.getName())))
                  .map(field -> {
                       try {
                            field.setAccessible(true);
                            return field.getName() + " = '" + field.get(t) + "'";
                       } catch (IllegalAccessException e) {
                            e.printStackTrace();
                       }
                       return "";
                  }).collect(Collectors.joining(", "));
     }

}
