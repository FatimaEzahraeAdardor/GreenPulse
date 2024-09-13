package GreenPulse.repository;
import GreenPulse.Config.DatabaseConnection;
import GreenPulse.Entites.*;
import GreenPulse.Entites.Enums.ConsumptionType;
import GreenPulse.Entites.Enums.EnergyType;
import GreenPulse.Entites.Enums.FoodType;
import GreenPulse.Entites.Enums.VihicleType;

import java.sql.*;
import java.util.*;

public class UserRepository {
    private Connection connection;
    public UserRepository() {
         connection = DatabaseConnection.getInstance().getConnection();
    }
    public User save(User user) {
        String query = "INSERT INTO users(name, age) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Optional<User> findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                User user = new User(name, age);
                user.setId(id);
                return Optional.of(user);
            } else {
                System.out.println("User not found with ID: " + id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> update(User user) {
        String query = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setLong(3, user.getId());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Boolean delete(int id){
        String query = "delete FROM users WHERE id = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1 , id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>(); // Initialize the list here

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }

        return users;
    }
    public List<User> getAllUsersWithConsumptions() {
        String query = "SELECT u.id, u.name, u.age, " +
                "       c.id as consumption_id, c.start_date, c.end_date, " +
                "       c.quantity, c.consumption_type, " +
                "       t.distance_traveled, t.vehicle_type, " +
                "       h.energy_consumption, h.energy_type, " +
                "       f.food_type, f.weight " +
                "FROM users u " +
                "LEFT JOIN consumptions c ON u.id = c.user_id " +
                "LEFT JOIN transport t ON c.id = t.consumption_id " +
                "LEFT JOIN housing h ON c.id = h.consumption_id " +
                "LEFT JOIN food f ON c.id = f.consumption_id";

        Map<Integer, User> userConsumptionsMap = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                int userAge = resultSet.getInt("age");

                User user = userConsumptionsMap.getOrDefault(userId, new User(userName, userAge));
                user.setId(userId);

                String consumptionType = resultSet.getString("consumption_type");
                if (consumptionType == null || consumptionType.isEmpty()) {
                    continue;
                }

                Consomation consumption = null;
                int consumptionId = resultSet.getInt("consumption_id");
                try {
                    switch (ConsumptionType.valueOf(consumptionType)) {
                        case HOUSING:
                            Housing housing = new Housing();
                            housing.setEnergyTypes(EnergyType.valueOf(resultSet.getString("energy_type")));
                            housing.setEnergyConsumption(resultSet.getDouble("energy_consumption"));
                            housing.setID(consumptionId);
                            housing.setStartDate(resultSet.getDate("start_date").toLocalDate());
                            housing.setEndDate(resultSet.getDate("end_date").toLocalDate());
                            housing.setQuantity(resultSet.getDouble("quantity"));
                            housing.setUser_id(userId);
                            consumption = housing;
                            break;
                        case FOOD:
                            Food food = new Food();
                            food.setFoodType(FoodType.valueOf(resultSet.getString("food_type")));
                            food.setWeight(resultSet.getDouble("weight"));
                            food.setID(consumptionId);
                            food.setStartDate(resultSet.getDate("start_date").toLocalDate());
                            food.setEndDate(resultSet.getDate("end_date").toLocalDate());
                            food.setQuantity(resultSet.getDouble("quantity"));
                            food.setUser_id(userId);
                            consumption = food;
                            break;

                        case TRANSPORT:
                            Transport transport = new Transport();
                            transport.setVehicleType(VihicleType.valueOf(resultSet.getString("vehicle_type")));
                            transport.setDistanceTraveled(resultSet.getDouble("distance_traveled"));
                            transport.setID(consumptionId);
                            transport.setStartDate(resultSet.getDate("start_date").toLocalDate());
                            transport.setEndDate(resultSet.getDate("end_date").toLocalDate());
                            transport.setQuantity(resultSet.getDouble("quantity"));
                            transport.setUser_id(userId);
                            consumption = transport;
                            break;
                    }

                    // Add consumption to the user if it's not null
                    if (consumption != null) {
                        user.getConsomation().add(consumption);
                    }

                    // Put the user back into the map
                    userConsumptionsMap.put(userId, user);

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid consumption_type: " + consumptionType);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching users with consumptions: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>(userConsumptionsMap.values());
    }




}
