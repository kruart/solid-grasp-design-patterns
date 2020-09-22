# Iterator
`Allows sequential traversal through a complex data structure 
without exposing its internal details`

Example:  

In this iterator pattern example, we are creating a collection 
which can holds instances of `Topic` class 
and will provide an iterator to iterate over topics collections in a sequence.

Topic
```java
record Topic(String name) {}

/* class without record feature 
public class Topic {
    private String name;

    public Topic(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 
*/
```

Iterator
```java
public interface Iterator<E> {
    void reset();   // reset to the first element
    E next();   // To get the next element
    E currentItem();    // To retrieve the current element
    boolean hasNext();  // To check whether there is any next element or not.
}
```

List
```java
public interface List<E> {
    Iterator<E> iterator();
}
```

`TopicList` inside itself implementing `Iterator` interface 
and return an instance of it on each `iterator()` method call
```java
public class TopicList implements List<Topic> {
    private final Topic[] topics;

    public TopicList(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public Iterator<Topic> iterator() {
        return new TopicIterator(topics);
    }

    private class TopicIterator implements Iterator<Topic> {
        private final Topic[] topics;
        private int position;

        public TopicIterator(Topic[] topics) {
            this.topics = topics;
            position = 0;
        }

        @Override
        public void reset() {
            position = 0;
        }

        @Override
        public Topic next() {
            return topics[position++];
        }

        @Override
        public Topic currentItem() {
            return topics[position];
        }

        @Override
        public boolean hasNext() {
            return position < topics.length;
        }
    }
}
```


The client code to use the iterator will be like this.
```java
public class Main {
    public static void main(String[] args) {
        Topic[] topics = new Topic[5];
        topics[0] = new Topic("topic1");
        topics[1] = new Topic("topic2");
        topics[2] = new Topic("topic3");
        topics[3] = new Topic("topic4");
        topics[4] = new Topic("topic5");

        List<Topic> list = new TopicList(topics);

        Iterator<Topic> iterator = list.iterator();

        while(iterator.hasNext()) {
            Topic currentTopic = iterator.next();
            System.out.println(currentTopic.name());
        }
    }
}
```