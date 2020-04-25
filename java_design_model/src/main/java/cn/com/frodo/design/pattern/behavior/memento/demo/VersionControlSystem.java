package cn.com.frodo.design.pattern.behavior.memento.demo;

import java.util.LinkedList;

public class VersionControlSystem {
    LinkedList<Backup> backups = new LinkedList<Backup>();
    int nextVersion;

    public void add(Backup backup) {
        backup.version = ++nextVersion;
        backups.add(backup);
    }

    public Backup get(int version) {
        for (Backup backup : backups) {
            if (backup.version == version) {
                return backup;
            }
        }
        return null;
    }

    public Backup getLastVersion() {
        return backups.getLast();
    }
}
