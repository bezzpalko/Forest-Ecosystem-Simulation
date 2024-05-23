package ecosystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class EcosystemSimulationTest {
    private EcosystemSimulation simulation;
    private Wolf wolf;
    private Deer deer;
    private Bird bird;

    @BeforeEach
    public void setUp() {
        simulation = new EcosystemSimulation(100, 100);
        wolf = new Wolf(100, 100, new Point(0, 0), "carnivore");
        deer = new Deer(100, 100, new Point(10, 10), "herbivore");
        bird = new Bird(100, 100, new Point(20, 20), "omnivore");
        simulation.addAnimal(wolf);
        simulation.addAnimal(deer);
        simulation.addAnimal(bird);
    }

    @Test
    public void testAddAnimal() {
        Wolf newWolf = new Wolf(100, 100, new Point(5, 5), "carnivore");
        simulation.addAnimal(newWolf);
        Assertions.assertTrue(simulation.getAnimals().contains(newWolf));
    }

    @Test
    public void testRemoveAnimal() {
        simulation.removeAnimal(wolf);
        Assertions.assertFalse(simulation.getAnimals().contains(wolf));
    }

    @Test
    public void testSimulate() {
        simulation.simulate();
        // Check if states are updated, assuming that move method modifies position
        Assertions.assertNotEquals(new Point(0, 0), wolf.getPosition());
        Assertions.assertNotEquals(new Point(10, 10), deer.getPosition());
        Assertions.assertNotEquals(new Point(20, 20), bird.getPosition());
    }

    @Test
    public void testSaveSimulationDataToCSV() {
        String filename = "test_simulation_data.csv";
        simulation.saveSimulationDataToCSV(filename);

        File file = new File(filename);
        assertTrue(file.exists(), "CSV file should be created");

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            assertFalse(lines.isEmpty(), "CSV file should not be empty");
            assertEquals("ecosystem.Animal,Energy,Health,Diet,Position,Wingspan/IsPackLeader/HasAntlers", lines.get(0), "CSV header should be correct");
        } catch (IOException e) {
            fail("IOException should not occur");
        }

        file.delete();  // Clean up
    }
}