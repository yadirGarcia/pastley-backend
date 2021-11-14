package com.pastley.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pastley.model.entity.User;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	/**
	 * 
	 * @param idRole
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT u.* FROM user AS u INNER JOIN user_role AS ur ON(u.id = ur.id_user AND ur.id_role = :idRole)")
	public List<User> findByIdRole(Long idRole);	
	
	/**
	 * 
	 * @param document
	 * @return
	 */
	@Query(nativeQuery = false, value = "SELECT u FROM User u WHERE u.person.document = :document")
	public User findByDocumentPerson(Long document);	
	
	/**
	 * 
	 * @param id
	 * @param idRole
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT u.* FROM user AS u INNER JOIN user_role AS ur ON(u.id = ur.id_user AND ur.id_role = :idRole) WHERE u.id = :id")
	public User findByIdAndIdRol(Long id, Long idRole);
	
    /**
     * 
     * @param nickname
     * @return
     */
    public User findByNickname(String nickname);
    
    /**
     * 
     * @param id
     * @param idRole
     * @return
     */
    @Query(nativeQuery= true, value ="INSERT INTO user_role(id_user, id_role) values (:id, :idRole)")
    public User createUserRole(Long id, Long idRole);
  
}
