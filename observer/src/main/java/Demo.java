public class Demo {
    public static void main(String[] args) {
        NewsAgency observable = new NewsAgency();

        NewsChannel observer1 = new NewsChannel();
        NewsChannel observer2 = new NewsChannel();

        observable.addPropertyChangeListener(observer1);
        observable.addPropertyChangeListener(observer2);
        observable.setNews("news");

        System.out.println(observer1.getNews());
        System.out.println(observer2.getNews());
    }
}
