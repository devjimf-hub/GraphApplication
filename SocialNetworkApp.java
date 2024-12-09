import java.util.*;

public class SocialNetworkApp {

    static class SocialNetwork {
        private Map<String, Set<String>> network;

        public SocialNetwork() {
            network = new HashMap<>();
        }

        // Add a person to the network
        public void addPerson(String name) {
            network.putIfAbsent(name, new HashSet<>());
        }

        // Add a friendship between two people
        public void addFriendship(String person1, String person2) {
            if (!network.containsKey(person1) || !network.containsKey(person2)) {
                System.out.println("One or both persons are not in the network.");
                return;
            }
            network.get(person1).add(person2);
            network.get(person2).add(person1);
        }

        // Display all people in the network
        public void displayNetwork() {
            System.out.println("\nSocial Network:");
            for (String person : network.keySet()) {
                System.out.println(person + " -> " + network.get(person));
            }
        }

        // Display friends of a person
        public void displayFriends(String person) {
            if (!network.containsKey(person)) {
                System.out.println(person + " is not in the network.");
                return;
            }
            System.out.println(person + "'s Friends: " + network.get(person));
        }

        // Suggest friends (friends of friends who are not already direct friends)
        public void suggestFriends(String person) {
            if (!network.containsKey(person)) {
                System.out.println(person + " is not in the network.");
                return;
            }

            Set<String> friends = network.get(person);
            Set<String> suggestions = new HashSet<>();

            for (String friend : friends) {
                for (String friendOfFriend : network.get(friend)) {
                    if (!friendOfFriend.equals(person) && !friends.contains(friendOfFriend)) {
                        suggestions.add(friendOfFriend);
                    }
                }
            }

            System.out.println("Friend Suggestions for " + person + ": " + (suggestions.isEmpty() ? "None" : suggestions));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork socialNetwork = new SocialNetwork();

        System.out.println("Welcome to the Social Network Application!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Person");
            System.out.println("2. Add Friendship");
            System.out.println("3. Display Network");
            System.out.println("4. Display Friends");
            System.out.println("5. Suggest Friends");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter person's name: ");
                    String name = scanner.nextLine();
                    socialNetwork.addPerson(name);
                    System.out.println(name + " has been added to the network.");
                }
                case 2 -> {
                    System.out.print("Enter the first person's name: ");
                    String person1 = scanner.nextLine();
                    System.out.print("Enter the second person's name: ");
                    String person2 = scanner.nextLine();
                    socialNetwork.addFriendship(person1, person2);
                }
                case 3 -> socialNetwork.displayNetwork();
                case 4 -> {
                    System.out.print("Enter person's name to view friends: ");
                    String person = scanner.nextLine();
                    socialNetwork.displayFriends(person);
                }
                case 5 -> {
                    System.out.print("Enter person's name to suggest friends: ");
                    String person = scanner.nextLine();
                    socialNetwork.suggestFriends(person);
                }
                case 6 -> {
                    System.out.println("Thank you for using the Social Network Application. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
