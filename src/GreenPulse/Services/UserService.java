package GreenPulse.Services;
import GreenPulse.Entites.Consomation;
import GreenPulse.Entites.User;
import GreenPulse.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    //find user with consumption depass 3000 :
    public List<User> FindserWithConsumption(){
        return userRepository.getAllUsersWithConsumptions()
                .stream().filter(e-> e.getConsomation().stream().mapToDouble(Consomation::calculerImpact).sum() > 3000.).collect(Collectors.toList());

    }


}
