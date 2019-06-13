package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private AtomicLong idCounter = new AtomicLong();
    private Map<Long, TimeEntry> inMemoryRepoMap = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {

        if (timeEntry.getId() == 0) {
            timeEntry.setId(idCounter.incrementAndGet());
        }
        inMemoryRepoMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return inMemoryRepoMap.get(id);
    }

    public List<TimeEntry> list() {
        return (inMemoryRepoMap instanceof List) ? (List<TimeEntry>) inMemoryRepoMap.values() : new ArrayList<>(inMemoryRepoMap.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if (!inMemoryRepoMap.containsKey(id)) {
            return null;
        }

        if (timeEntry.getId() == 0 ) {
            timeEntry.setId(id);
        }
        inMemoryRepoMap.put(id, timeEntry);
        return timeEntry;
    }

    public TimeEntry delete(long id) {
        return inMemoryRepoMap.remove(id);

    }
}
