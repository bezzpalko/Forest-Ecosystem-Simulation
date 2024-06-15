package ecosystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Test class for EcosystemSimulation.
 */
public class EcosystemSimulationTest {
    private EcosystemSimulation simulation;
    private Wolf wolf;
    private Deer deer;
    private Bird bird;
    private Tree tree;
    private Bush bush;
    private Flower flower;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        simulation = new EcosystemSimulation(20, 20);
        wolf = new Wolf(100, 100, new Point(0, 0), "carnivorous");
        deer = new Deer(100, 100, new Point(10, 10), "herbivorous");
        bird = new Bird(100, 100, new Point(20, 20), "omnivorous");
        simulation.addAnimal(wolf);
        simulation.addAnimal(deer);
        simulation.addAnimal(bird);
        tree = new Tree(100, 100, "growth", new Point(5, 5));
        bush = new Bush(80, 80, "growth", new Point(15, 15));
        flower = new Flower(90, 90, "blooming", new Point(3, 5));
    }

    /**
     * Tests adding an animal to the simulation.
     */
    @Test
    public void testAddAnimal() {
        Wolf newWolf = new Wolf(100, 100, new Point(5, 5), "carnivorous");
        simulation.addAnimal(newWolf);
        assertTrue(simulation.getAnimals().contains(newWolf), "Wolf should be added to the simulation.");
    }

    /**
     * Tests removing an animal from the simulation.
     */
    @Test
    public void testRemoveAnimal() {
        simulation.removeAnimal(wolf);
        assertFalse(simulation.getAnimals().contains(wolf), "Wolf should be removed from the simulation.");
    }

    /**
     * Tests adding a plant to the simulation.
     */
    @Test
    public void testAddPlant() {
        Tree newTree = new Tree(90, 90, "rest", new Point(10, 20));
        simulation.addPlant(newTree);
        assertTrue(simulation.getPlants().contains(newTree), "Tree should be added to the simulation.");
    }

    /**
     * Tests removing a plant from the simulation.
     */
    @Test
    public void testRemovePlant() {
        simulation.removePlant(tree);
        assertFalse(simulation.getPlants().contains(tree), "Tree should be removed from the simulation.");
    }

    /**
     * Tests the reaction of a plant to a rain event.
     */
    @Test
    public void testPlantReactToEvent() {
        Rain rain = new Rain();
        simulation.applyEventToEnvironment(rain);
        assertTrue(tree.getHealth() > 100, "Tree's health should increase after rain.");
        assertTrue(tree.getHydration() > 100, "Tree's hydration should increase after rain.");
    }

    /**
     * Tests the reaction of a bush to a drought event.
     */
    @Test
    public void testBushReactToEvent() {
        Drought drought = new Drought();
        simulation.applyEventToEnvironment(drought);
        assertTrue(bush.getHealth() < 80, "Bush's health should decrease after drought.");
        assertTrue(bush.getHydration() < 80, "Bush's hydration should decrease after drought.");
    }

    /**
     * Tests the reaction of a flower to a storm event.
     */
    @Test
    public void testFlowerReactToEvent() {
        Storm storm = new Storm();
        simulation.applyEventToEnvironment(storm);
        assertTrue(flower.getHealth() < 90, "Flower's health should decrease after storm.");
    }

    /**
     * Tests the simulation step to ensure animals move.
     */
    @Test
    public void testSimulate() {
        simulation.simulate();
        assertNotEquals(new Point(0, 0), wolf.getPosition(), "Wolf should have moved.");
        assertNotEquals(new Point(10, 10), deer.getPosition(), "Deer should have moved.");
        assertNotEquals(new Point(20, 20), bird.getPosition(), "Bird should have moved.");
    }

    /**
     * Tests saving the simulation data to a CSV file.
     */
    @Test
    public void testSaveSimulationDataToCSV() {
        String filename = "test_simulation_data.csv";
        simulation.saveSimulationDataToCSV(filename);

        File file = new File(filename);
        assertTrue(file.exists(), "CSV file should be created");

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            assertFalse(lines.isEmpty(), "CSV file should not be empty.");
            assertEquals("ecosystem.Animal,Energy,Health,Diet,Position,Wingspan/IsPackLeader/HasAntlers",
                    lines.get(0), "CSV header should be correct.");
        } catch (IOException e) {
            fail("IOException should not occur");
        }

        file.delete();  // Clean up
    }
}