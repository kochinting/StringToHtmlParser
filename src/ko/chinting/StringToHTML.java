package ko.chinting;
import java.util.*;

/**
 * This program is to parse Twitter Feeds to HTML format.
 * The code is designed to follow OOD principles
 *
 * Created by TimKo on 4/28/17.
 */
public class StringToHTML {

    public enum Concept {
        Entity, UserName, Link, HashTag
    }

    //Feed interface class
    public interface Feed {
        void setInputString(String input);
        String getInputString();
        void setConceptName(int a, int b, Concept concept);
        String getConceptName(int a, int b);
        String output();
    }

    //Implementation of Twitter Feed
    public static class TwitterFeed implements Feed {
        private StringBuilder inputString;
        private StringBuilder outputString;

        private List<String> entityList;
        private List<String> userNameList;
        private List<String> linkList;

        private int entityIndex;
        private int userNameIndex;
        private int linkIndex;

        public TwitterFeed() {
            this.inputString = new StringBuilder();
            this.outputString = new StringBuilder();
            this.entityList = new ArrayList<>();
            this.userNameList = new ArrayList<>();
            this.linkList = new ArrayList<>();
            entityIndex = 0;
            userNameIndex = 0;
            linkIndex = 0;
        }

        public String getInputString() {
            return inputString.toString();
        }

        public void setInputString(String input) {
            inputString.append(input);
            outputString.append(input);
        }

        public String getConceptName(int a, int b) {
            return inputString.substring(a, b);
        }

        public void setConceptName(int a, int b, Concept concept) {
            switch (concept) {
                case Entity:
                    entityList.add(getConceptName(a, b));
                    outputString.insert((outputString.indexOf(entityList.get(entityIndex)) + entityList.get(entityIndex).length()), "</strong>");
                    outputString.insert(outputString.indexOf(entityList.get(entityIndex)), "<strong>");
                    entityIndex++;
                    break;
                case UserName:
                    userNameList.add(inputString.substring(a, b));
                    outputString.insert((outputString.indexOf(userNameList.get(userNameIndex)) + userNameList.get(userNameIndex).length()), "</a>");
                    outputString.insert(outputString.indexOf(userNameList.get(userNameIndex)), "<a href=\"http://twitter.com/" + userNameList.get(userNameIndex) + "\">");
                    userNameIndex++;
                    break;
                case Link:
                    linkList.add(inputString.substring(a, b));
                    outputString.insert((outputString.indexOf(linkList.get(linkIndex)) + linkList.get(linkIndex).length()), "</a>");
                    outputString.insert(outputString.indexOf(linkList.get(linkIndex)), "<a href=\"" + linkList.get(linkIndex) + "\">");
                    linkIndex++;
                    break;
            }
        }

        public String output() {
            return outputString.toString();
        }
    }

    //Implementation of Facebook Feed
    public static class FacebookFeed implements Feed {
        public void setInputString(String input){}
        public String getInputString(){
            return "facebook input feed";
        }
        public void setConceptName(int a, int b, Concept concept){}
        public String getConceptName(int a, int b){
            return "facebook Feed Concept";
        }
        public String output(){
            return "facebook feed output";
        }
    }

    // abstract decorator class for Feed
    public static abstract class FeedDecorator implements Feed{
        protected final Feed decoratedFeed;

        public FeedDecorator (Feed decoratedFeed){
            this.decoratedFeed= decoratedFeed;
        }

        public void setInputString(String input){}

        public String getInputString(){
            return decoratedFeed.getInputString();
        }

        public void setConceptName(int a, int b, Concept concept){}

        public String getConceptName(int a, int b){
            return decoratedFeed.getConceptName(a,b);
        }

        public String output(){
            return decoratedFeed.output();
        }
    }

    // Decorator class with new type hash tags
    public static class FeedHashTagDecorator extends FeedDecorator{
        public FeedHashTagDecorator(Feed decoratedFeed){
            super(decoratedFeed);
        }

        public void setInputString(String input){}

        public String getInputString(){
            return super.getInputString();
        }

        public void setConceptName(int a, int b, Concept concept){
            // add HashTags feature here!
        }

        public String getConceptName(int a, int b){
            return super.getConceptName(a,b);
        }

        public String output(){
            return super.output();
        }
    }


}
