import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a person (infection target). The class implements the Susceptible and Movable interfaces.
 *
 * @author Arman Sidhu
 * @since 2020-07-30
 */
public class Person implements Susceptible, Movable {
    private Disease currentDisease;  // Composition
    private String name;
    private Point position;
    private List<Susceptible> othersInfected;
    private boolean isImmune;

    /**
     * Constructs a person object with a given position and name.
     * @param position the position of the person
     * @param name the name of the person
     */
    public Person(Point position, String name) {
        this.position = position.copy();
        this.name = name;
        currentDisease = null;
        othersInfected = new ArrayList<>();
        isImmune = false;
    }

    /**
     * Infects the person with a disease.
     * @param disease the Disease object
     * @return true if the person is infected; false otherwise
     */
    public boolean infect(Disease disease) {
        // Checking if the person is not already infected or immune
        if(disease != null && currentDisease == null && !isImmune) {
            // Calling the Disease's tryInfect method
            if(disease.tryInfect()) {
                // If successful, assign the disease to the instance variable
                currentDisease = disease;
                return true;
            }
        }
        return false;
    }

    /**
     * Infects the person ignoring chance and immunity.
     * @param disease the Disease object
     */
    public void forceInfection(Disease disease) {
        currentDisease = disease;
    }

    /**
     * Returns the person's current disease.
     * @return the current disease or null if healthy
     */
    public Disease getCurrentDisease() {
        return currentDisease;
    }

    /**
     * Makes the person immune to diseases.
     */
    public void setImmune() {
        isImmune = true;
    }

    /**
     * Returns if the person is immune or not.
     * @return true if the person is immune; false otherwise
     */
    public boolean isImmune() {
        return isImmune;
    }

    /**
     * Returns the position of the susceptible person.
     * @return a Point object representing the position of the person in the world
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Moves the person in a random x and y-direction from 0 up to a maximum of step (not
     * included). There is a 50% chance that the movement is backwards.
     *
     * @param step an int representing the maximum x and y coordinate values for the movement
     */
    public void move(int step) {
        // Creating a random object
        Random rand = RandomNumbers.rnd;

        // Generating random x and y coordinates within the step limit
        int x = rand.nextInt(step);
        int y = rand.nextInt(step);

        // Testing for a 50% chance of success
        if(rand.nextDouble() <= .5) {
            // Backward translation
            position.translate(-x, -y);
        }
        else {
            // Forward translation
            position.translate(x, y);
        }

        // Setting the x and y coordinates to zero if they are less than 0

        if(position.getxCoordinate() < 0) {
            position.setxCoordinate(0);
        }

        if(position.getyCoordinate() < 0) {
            position.setyCoordinate(0);
        }

    }

    /**
     * Adds the infected susceptible to the list containing susceptibles infected by this person.
     * @param susceptible the infected susceptible object
     */
    public void addToTracing(Susceptible susceptible) {
        othersInfected.add(susceptible);
    }

    /*
    /**
     * Returns a list of susceptibles directly infected by this person.
     * @return a list containing susceptibles directly infected by this person
     */
    /*
    public List<Susceptible> getOthersInfected() {
        return othersInfected;
    }
    */

    /**
     * Returns a list of susceptibles directly and indirectly infected by this person.
     * @return a list containing susceptibles directly and indirectly infected by this person
     */
    public List<Susceptible> getOthersInfected() {
        // Creating a list to store everyone infected (directly and indirectly)
        List<Susceptible> everyoneInfected = new ArrayList<>();

        // Checking if othersInfected (or directly infected susceptibles list) is not empty
        if(!othersInfected.isEmpty()) {
            // Appending the directly infected susceptibles to everyoneInfected
            everyoneInfected.addAll(othersInfected);

            // Iterating over othersInfected
            for(Susceptible s : othersInfected) {
                // Checking if the susceptible is an instance of Person
                if(s instanceof Person) {
                    // Casting susceptible to Person
                    Person p = (Person) s;

                    // Recursively appending susceptibles directly infected by this person to everyoneInfected
                    everyoneInfected.addAll(p.getOthersInfected());
                }
            }
        }
        // Returning everyoneInfected
        return everyoneInfected;
    }

    /**
     * Returns the name of the person.
     * @return a String representing the name of the person
     */
    @Override
    public String toString() {
        return name;
    }
}
