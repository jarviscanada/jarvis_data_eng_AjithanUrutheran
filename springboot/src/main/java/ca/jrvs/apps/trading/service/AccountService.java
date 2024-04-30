//package ca.jrvs.apps.trading.service;
//
//import ca.jrvs.apps.trading.entity.Account;
//import ca.jrvs.apps.trading.repository.AccountJpaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//public class AccountService {
//
//    private AccountJpaRepository accountRepo;
//
//    @Autowired
//    public AccountService(AccountJpaRepository accountRepo) {
//        this.accountRepo = accountRepo;
//    }
//
//    /**
//     * Deletes the account if the balance is 0
//     * @param traderId cannot be null
//     * @throws IllegalArgumentException if unable to delete
//     */
//    @Transactional
//    public void deleteAccountByTraderId(Integer traderId) {
//        Account account = accountRepo.getAccountByTraderId(traderId);
//        if (account.getAmount() != 0) {
//            throw new IllegalArgumentException("Balance not 0");
//        }
//        accountRepo.deleteById(account.getId());
//    }
//
//}