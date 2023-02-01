package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return account.equals(accounts.putIfAbsent(account.id(), account));
    }

    public synchronized boolean update(Account account) {
        return getById(account.id()).get().equals(accounts.put(account.id(), account));
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var accForTransferFrom = getById(fromId);
        var accForTransferTo = getById(toId);
        if (accForTransferFrom.isEmpty() || accForTransferTo.isEmpty() || accForTransferFrom.get().amount() - amount < 0) {
            return false;
        }
        Account account1 = new Account(fromId, accForTransferFrom.get().amount() - amount);
        Account account2 = new Account(toId, accForTransferTo.get().amount() + amount);
        update(account1);
        update(account2);
        return true;
    }

}