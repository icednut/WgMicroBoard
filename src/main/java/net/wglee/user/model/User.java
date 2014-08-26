package net.wglee.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author wangeun.lee@sk.com
 */
@Getter
@Setter
@ToString
@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "roles")
	private String roles;
}
