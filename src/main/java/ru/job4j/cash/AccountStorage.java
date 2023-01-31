package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public synchronized boolean add(Account account) {
        if (accounts.containsKey(account.id())) {
            return false;
        }
        return accounts.put(atomicInteger.incrementAndGet(), account) != null;
    }

    public synchronized boolean update(Account account) {
        var accountFromStorage = getById(account.id());
        if (accountFromStorage.isEmpty()) {
            throw new NoSuchElementException("Have Not this Account in Storage!");
        }
        return accounts.put(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var acc1 = getById(fromId);
        var acc2 = getById(toId);
        if (acc1.isEmpty() || acc2.isEmpty() || acc1.get().amount() - amount < 0) {
            return false;
        }
        int minus = acc1.get().amount();
        minus -= amount;
        int plus = acc2.get().amount();
        plus += amount;
        Account account1 = new Account(fromId, minus);
        Account account2 = new Account(toId, plus);
        update(account1);
        update(account2);
        return true;
    }

}