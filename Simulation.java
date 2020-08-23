import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class creating a simulation to represent the transmission of disease.
 *
 * @author Arman Sidhu
 * @since 2020-07-30
 */
public class Simulation {
    private static final int MAX_MOVEMENT_STEP = 50;
    private List<Susceptible> simulationEntities;

    /**
     * Sets up a simulation as per the given inputs.
     *
     * @param numberOfSusceptible the number of susceptibles
     * @param numberOfInfected  the number of infected susceptibles
     * @param disease the Disease object
     * @param numberOfImmune the number of immune susceptibles
     * @param gridSize the size of the grid
     */
    public Simulation(int numberOfSusceptible, int numberOfInfected, Disease disease, int numberOfImmune, int gridSize) {
        simulationEntities = new ArrayList<>();

        // Creating the susceptible objects
        for(int i = 0; i < numberOfSusceptible; i++) {
            Random rand = RandomNumbers.rnd;

            // Positioning the susceptibles using random x and y coordinates within the grid size
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);

            // Adding the simulation entities to the list
            simulationEntities.add(new Person(new Point(x, y), "Person " + i));
        }

        // Forcefully infecting the first numberInfected Susceptibles in the List
        for(int i = 0; i < numberOfInfected; i++) {
            simulationEntities.get(i).forceInfection(disease);
        }

        // Setting the last numberOfImmune Susceptibles in the List to immune
        for(int i = simulationEntities.size() - 1; i >= simulationEntities.size() - numberOfImmune; i--) {
            simulationEntities.get(i).setImmune();
        }
    }

    /**
     * Returns the simulation entities.
     * @return a list containing the simulation entities
     */
    public List<Susceptible> getEntities() {
        return simulationEntities;
    }

    /**
     * Steps the simulation by moving the movable entities and trying to infect the healthy susceptibles.
     */
    public void stepSimulation() {

        // Moving all the entities that implement the Movable interface
        for(Susceptible susceptible : simulationEntities) {

            // Checking if susceptible is an instance of Movable
            if(susceptible instanceof Movable) {
                // Casting the susceptible to Movable
                Movable movable = (Movable) susceptible;
                // Calling the move method
                movable.move(MAX_MOVEMENT_STEP);
            }
        }

        // Infected susceptibles trying to infect the healthy entities
        for(int i = 0; i < simulationEntities.size(); i++) {
            Susceptible susceptible = simulationEntities.get(i);

            // Checking if the susceptible is infected
            if(susceptible.getCurrentDisease() != null) {

                for(int j = 0; j < simulationEntities.size(); j++) {
                    Susceptible otherSusceptible = simulationEntities.get(j);

                    // Calculating the distance between the two susceptibles
                    double distance = susceptible.getPosition().distance(otherSusceptible.getPosition());

                    // Checking if the distance is less than the infection radius of the disease
                    if(distance <= susceptible.getCurrentDisease().getInfectionRadius()) {

                        // Trying to infect the healthy susceptible
                        if(otherSusceptible.infect(susceptible.getCurrentDisease())) {

                            // Adding the newly infected susceptible to tracing if it is a person
                            if(susceptible instanceof Person) {
                                ((Person) susceptible).addToTracing(otherSusceptible);
                            }
                        }

                    }
                }
            }
        }
    }
}