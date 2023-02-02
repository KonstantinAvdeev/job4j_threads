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
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.put(account.id(), account) != null;
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
        update(new Account(fromId, accForTransferFrom.get().amount() - amount));
        update(new Account(toId, accForTransferTo.get().amount() + amount));
        return true;
    }

}