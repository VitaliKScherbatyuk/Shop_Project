package scherbatyuk.shops.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import scherbatyuk.shops.domain.User;

public interface UserRepository extends JpaRepository <User, Integer>{
	Optional<User> findByEmail(String email);
}
