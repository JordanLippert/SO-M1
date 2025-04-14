package server;

import db.Database;
import db.Record;

public class ThreadWorker implements Runnable{
    private final String command;
    private final Database db;

    public ThreadWorker(String command, Database db) {
        this.command = command;
        this.db = db;
    }

    @Override
    public void run() {
        if (command.startsWith("INSERT")) {
            String[] parts = command.split(" ",3);
            int id = Integer.parseInt(parts[1]);
            String nome = parts[2];
            db.insert(new Record(id, nome));
        } else if (command.startsWith("DELETE")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            db.delete(id);
        } else if (command.startsWith("SELECT")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            Record record = db.select(id);
            System.out.println("SELECT: " + (record != null ? record: "ID=" + id + " não encontrado"));
        } else if (command.startsWith("UPDATE")) {
            String[] parts = command.split("", 3);
            int id = Integer.parseInt(parts[1]);
            String nome = parts[2];
            db.update(id, nome);
        } else {
            System.out.println("Comando inválido: " + command);
        }
    }
}
