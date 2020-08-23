import java.util.Random;

/**
 * Class representing a Disease with a specific infection probability and radius.
 *
 * @author Arman Sidhu
 * @since 2020-07-30
 */
public class Disease {
    private String name;
    private double infectionProbability;
    private int infectionRadius;

    /**
     * Constructs a disease with a given name, infection probability and infection radius.
     * @param name the name of the disease
     * @param infectionProbability the infection probability of the disease
     * @param infectionRadius the infection radius of the disease
     */
    public Disease(String name, double infectionProbability, int infectionRadius) {
        this.name = name;
        this.infectionProbability = infectionProbability;
        this.infectionRadius = infectionRadius;
    }

    /**
     * Returns the name of the disease.
     * @return the name of the disease
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the infection probability of the disease.
     * @return the infection probability of the disease
     */
    public double getInfectionProbability() {
        return infectionProbability;
    }

    /**
     * Returns the infection radius of the disease.
     * @return the infection radius of the disease
     */
    public int getInfectionRadius() {
        return infectionRadius;
    }

    /**
     * Tries to infect the healthy susceptible using the infection probability value.
     * @return true if the healthy susceptible is now infected; false otherwise
     */
    public boolean tryInfect() {
        // Creating a random object
        Random rand = RandomNumbers.rnd;

        // Testing if the susceptible is infected using the infection probability value
        return rand.nextDouble() <= infectionProbability;
    }

}