package GreenPulse.Services;
import GreenPulse.Entites.User;
import GreenPulse.repository.UserRepository;

import java.util.List;
import java.util.Optional;
public class UserService {
    UserRepository userRepository = new UserRepository();
     public User create(User user){
         return userRepository.save(user);
     }
     public Optional<User> update(User user){
         Optional<User> existingUser = findById(user.getId());
         if(existingUser.isPresent()) {
             return userRepository.update(user);
         }else {
             return Optional.empty();
         }
     }
     public boolean delete(User user){
         Optional<User> existingUser = findById(user.getId());
         if (existingUser.isPresent()){
             userRepository.delete(user.getId());
         }
         return true;
     }
    public Optional<User> findById(int id){
        return userRepository.findById(id);

    }
    public List<User> findAll(){
         return userRepository.findAll();
    }

    public Optional<User> getUserWithConsumptions(int id) {
        return userRepository.getAllUsersWithConsumptions().stream()
                .filter(user -> Integer.valueOf(user.getId()).equals(id))
                .findFirst();
    }
}
