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