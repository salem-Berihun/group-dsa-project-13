import java.util.HashMap;

// Node class for each song
class SongNode {
    String title;      // the actual song title
    SongNode prev;     // previous song in MRU->LRU order
    SongNode next;     // next song in MRU->LRU order

    public SongNode(String title) {
        this.title = title;
        this.prev = null;
        this.next = null;
    }
}

// LRU Cache implementation
class MusicCache {
    private int capacity;
    private HashMap<String, SongNode> songMap;
    private SongNode mostRecent; // head of the doubly linked list
    private SongNode leastRecent; // tail of the doubly linked list

    public MusicCache(int capacity) {
        this.capacity = capacity;
        this.songMap = new HashMap<>();
        this.mostRecent = null;
        this.leastRecent = null;
    }

    private void addToFront(SongNode node) {
        node.prev = null;
        node.next = mostRecent;
        if (mostRecent != null) {
            mostRecent.prev = node;
        }
        mostRecent = node;
        if (leastRecent == null) {
            leastRecent = node;
        }
    }

    private void removeNode(SongNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            mostRecent = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            leastRecent = node.prev;
        }
    }

    private void moveToFront(SongNode node) {
        if (node == mostRecent) return;
        removeNode(node);
        addToFront(node);
    }

    public void playSong(String title) {
        if (songMap.containsKey(title)) {
            moveToFront(songMap.get(title));
        } else {
            SongNode newNode = new SongNode(title);
            if (songMap.size() == capacity) {
                System.out.println("Evicting '" + leastRecent.title + "' (Least Recently Used).");
                songMap.remove(leastRecent.title);
                removeNode(leastRecent);
            }
            addToFront(newNode);
            songMap.put(title, newNode);
        }
    }

    public void showCache() {
        SongNode current = mostRecent;
        System.out.print("Cache (MRU -> LRU): ");
        while (current != null) {
            System.out.print(current.title);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}
