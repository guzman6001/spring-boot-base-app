package com.xigmad.baseapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xigmad.baseapp.entities.UserEntity;

public interface UsersRepositoryInt extends JpaRepository <UserEntity,Integer>{
	UserEntity findByUsername (String username);
}
