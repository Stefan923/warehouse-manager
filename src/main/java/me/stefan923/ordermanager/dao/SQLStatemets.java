package me.stefan923.ordermanager.dao;

public final class SQLStatemets {

    public static final String SELECT_ALL = "SELECT * FROM `%table%`;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM `%table%` WHERE `%field%` = ?;";
    public static final String SELECT_ALL_LIKE_FIELD = "SELECT * FROM `%table%` WHERE `%field%` LIKE CONCAT('%', ?, '%');";

    public static final String INSERT = "INSERT INTO `%table%` (%fields%) VALUES (%field_values%);";
    public static final String UPDATE_BY_FIELD = "UPDATE `%table%` SET %fields% WHERE %field% = ?;";
    public static final String DELETE_BY_FIELD = "DELETE FROM `%table%` WHERE `%field%` = ?;";

    private SQLStatemets() { }

}
