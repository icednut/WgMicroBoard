package net.wglee.user.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author wangeun.lee@sk.com
 */
@Data
@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;
}
