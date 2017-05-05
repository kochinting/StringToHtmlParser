package ko.chinting;


public class Main {

    public static void main(String[] args) {
	// write your code here

        String input= "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile";

        StringToHTML.Feed twitterFeeds1= new StringToHTML.TwitterFeed();
        twitterFeeds1.setInputString(input);

        twitterFeeds1.setConceptName(14,22, StringToHTML.Concept.Entity);
        String entity1= twitterFeeds1.getConceptName(14,22);
        System.out.println("entity1: "+entity1);

        twitterFeeds1.setConceptName(0,5, StringToHTML.Concept.Entity);
        String entity2= twitterFeeds1.getConceptName(0,5);
        System.out.println("entity2: "+entity2);

        twitterFeeds1.setConceptName(56,67, StringToHTML.Concept.UserName);
        String userName1= twitterFeeds1.getConceptName(56,67);
        System.out.println("userName1: "+userName1);

        twitterFeeds1.setConceptName(37,54, StringToHTML.Concept.Link);
        String link1= twitterFeeds1.getConceptName(37,54);
        System.out.println("link1: "+link1);

        String getInput=twitterFeeds1.getInputString();
        System.out.println("input: "+getInput);

        String result= twitterFeeds1.output();
        System.out.println("output: "+result);

        // test decorator code here
        StringToHTML.Feed twitterFeeds1WithHashTag= new StringToHTML.FeedHashTagDecorator(new StringToHTML.TwitterFeed());
        StringToHTML.Feed facebookFeed1= new StringToHTML.FacebookFeed();
        StringToHTML.Feed facebookFeed1WithHashTag= new StringToHTML.FeedHashTagDecorator(new StringToHTML.FacebookFeed());

    }
}
