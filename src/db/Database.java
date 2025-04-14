package db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    private final List<Record> records = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock(); //o lock vai servir como um mutex

    public void insert(Record record) {
        lock.lock();
        try {
            records.add(record);
            System.out.println("INSERT: " + record);
        } finally {
            lock.unlock();
        }
    }

    public void delete(int id) {
        lock.lock();
        try {
            records.removeIf(record -> record.getId() == id);
            System.out.println("DELETE ID=" + id);
        } finally {
            lock.unlock();
        }
    }

    public Record select(int id) {
        lock.lock();
        try {
            return records.stream().filter(record -> record.getId() == id).findFirst().orElse(null);
        } finally {
            lock.unlock();
        }
    }

    public void update(int id, String nome) {
        lock.lock();
        try {
            for (Record record : records) {
                if (record.getId() == id) {
                    record.setNome(nome);
                    System.out.println("UPDATE: " + record);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Record> getAll() {
        lock.lock();
        try {
            return new ArrayList<>(records);
        } finally {
            lock.unlock();
        }
    }
}
