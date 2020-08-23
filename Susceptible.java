/**
 * This interface can be implemented by any class representing entities that
 * can be infected or infect other susceptible objects (e.g human, chicken, insects).
 *
 * @author Arman Sidhu
 * @since 2020-07-29
 */
public interface Susceptible {

    /**
     * Infects the object with a disease.
     * @param disease the Disease object
     * @return true if the object is infected; false otherwise
     */
    public boolean infect(Disease disease);

    /**
     * Infects the object ignoring chance and immunity.
     * @param disease the Disease object
     */
    public void forceInfection(Disease disease);

    /**
     * Returns the object's current disease.
     * @return the current disease or null if healthy
     */
    public Disease getCurrentDisease();

    /**
     * Makes the object immune to diseases.
     */
    public void setImmune();

    /**
     * Returns if the object is immune or not.
     * @return true if the object is immune; false otherwise
     */
    public boolean isImmune();

    /**
     * Returns the position of the susceptible object.
     * @return a Point object representing the position of the susceptible object in the world
     */
    public Point getPosition();

}