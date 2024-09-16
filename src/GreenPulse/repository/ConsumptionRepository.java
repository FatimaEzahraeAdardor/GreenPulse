package GreenPulse.repository;

import GreenPulse.Config.DatabaseConnection;
import GreenPulse.Entites.Consomation;
import GreenPulse.Entites.Food;
import GreenPulse.Entites.Housing;
import GreenPulse.Entites.Transport;
import java.sql.*;

public class ConsumptionRepository {
    private Connection connection;

    public ConsumptionRepository() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public int create(Consomation consumption) throws SQLException {
        String query = "INSERT INTO consumptions(start_date, end_date, quantity, consumption_type, user_id) values (?,?,?,?::consumption_type,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, java.sql.Date.valueOf(consumption.getStartDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(consumption.getEndDate()));
        preparedStatement.setDouble(3, consumption.getQuantity());
        preparedStatement.setObject(4, consumption.getConsumptionType().name());
        preparedStatement.setLong(5, consumption.getUser_id());
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            consumption.setID(rs.getInt(1));
        }
        return consumption.getID();
    }

    public Food createFood(Food food) throws SQLException {
        int c_id =-1;
        try {
            connection.setAutoCommit(false);
            c_id = create(food);
            String query = "INSERT INTO food(food_type, weight, consumption_id) values(?::food_type,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, food.getFoodType().name());
            preparedStatement.setDouble(2, food.getWeight());
            preparedStatement.setInt(3, c_id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return food;

    }

    public Housing createHousing(Housing housing) throws SQLException {
        int c_id =-1;
         try{
             connection.setAutoCommit(false);
             c_id = create(housing);
            String query = "INSERT INTO housing(energy_type, energy_consumption, consumption_id) values(?::energy_type,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, housing.getEnergyTypes().name());
            preparedStatement.setDouble(2, housing.getEnergyConsumption());
            preparedStatement.setInt(3, c_id);
            preparedStatement.executeUpdate();
           connection.commit();
            } catch (SQLException e) {
                if (connection != null) {
                    connection.rollback();
                }
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }

         return housing;
    }
    public Transport createTransport(Transport transport) throws SQLException {
        int c_id;
        try {
        c_id = create(transport);
        connection.setAutoCommit(false);
        String query = "INSERT INTO transport(vehicle_type, distance_traveled, consumption_id) values(?::vihicle_type,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, transport.getVehicleType().name());
        preparedStatement.setDouble(2, transport.getDistanceTraveled());
        preparedStatement.setInt(3, c_id);
        preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return transport;
    }
}
