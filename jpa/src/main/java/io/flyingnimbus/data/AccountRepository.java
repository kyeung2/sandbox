package io.flyingnimbus.data;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //Note: Spring Data also has support using QueryDsl and their Predicate objects for a fluent interface

    List<Account> findByEmail(String email);

    // sorting, can also be in the name, but Sort has more flexibility
    List<Account> findByPassword(String password, Sort sort);

    // aggregate functions
    long countByEmail(String email);

    // using SQL directly
    @Query(value = "SELECT * FROM account WHERE email = ?1", nativeQuery = true)
    Account findByEmailNative(String email);


    // explicit version
    @Modifying // denoting it is not a select
    @Query("delete from Account acc where acc.id = ?1")// using Spring Expression Language
    void deleteInBulkByRoleId(long id);
}
