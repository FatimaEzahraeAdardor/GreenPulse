package GreenPulse.Services;
import GreenPulse.Entites.Consomation;
import GreenPulse.Entites.User;
import GreenPulse.repository.UserRepository;
import GreenPulse.utils.DateChecker;

import java.time.LocalDate;
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
    public List<User> getUsersInactifs(LocalDate start , LocalDate end){
        List<LocalDate> period = DateChecker.dateList(start, end);
        List<User> user = userRepository.getAllUsersWithConsumptions();
       return user.stream().filter(e -> e.getConsomation().stream().filter(c -> !DateChecker.isDateAvailable(c.getStartDate(), c.getEndDate(), period)).collect(Collectors.toList()).isEmpty()).collect(Collectors.toList());
    }
    public Double calculAverageConsumption(int id , LocalDate start , LocalDate end){
         Optional<User> user = getUserWithConsumptions(id);
         List<LocalDate> periode = DateChecker.dateList(start ,end);
         return user.get().getConsomation()
                 .stream()
                 .filter(e->!DateChecker.isDateAvailable(e.getStartDate(), e.getEndDate(),periode)).mapToDouble(Consomation::calculerImpact).sum()/ periode.size();

    }





}
