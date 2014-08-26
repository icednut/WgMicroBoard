package net.wglee.user.repository;

import net.wglee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author wangeun.lee@sk.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}