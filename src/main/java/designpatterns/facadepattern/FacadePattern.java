// Facade Design Pattern

package designpatterns.facadepattern;

public class FacadePattern {

    // Subsystem class: handles CPU operations independently
    static class CPU {
        void freeze() {
            System.out.println("CPU: Freezing processor");
        }

        void jump(long position) {
            System.out.println("CPU: Jumping to position " + position);
        }

        void execute() {
            System.out.println("CPU: Executing instructions");
        }
    }

    // Subsystem class: handles memory loading
    static class Memory {
        void load(long position, byte[] data) {
            System.out.println("Memory: Loading " + data.length + " bytes at position " + position);
        }
    }

    // Subsystem class: handles hard drive read operations
    static class HardDrive {
        byte[] read(long lba, int size) {
            System.out.println("HardDrive: Reading " + size + " bytes from sector " + lba);
            return new byte[size];
        }
    }

    // Facade: provides a simple interface that hides the complexity of coordinating
    // CPU, Memory, and HardDrive subsystems during startup
    static class ComputerFacade {
        private static final long BOOT_ADDRESS = 0x00;
        private static final long BOOT_SECTOR = 0x01;
        private static final int SECTOR_SIZE = 512;

        private CPU cpu;
        private Memory memory;
        private HardDrive hardDrive;

        ComputerFacade() {
            this.cpu = new CPU();
            this.memory = new Memory();
            this.hardDrive = new HardDrive();
        }

        // Single method replaces a multi-step boot sequence the client would otherwise manage
        void start() {
            System.out.println("=== Starting Computer ===");
            cpu.freeze();
            // Read boot data from disk, load it into memory, then execute from boot address
            byte[] bootData = hardDrive.read(BOOT_SECTOR, SECTOR_SIZE);
            memory.load(BOOT_ADDRESS, bootData);
            cpu.jump(BOOT_ADDRESS);
            cpu.execute();
            System.out.println("=== Computer Started ===\n");
        }

        void shutdown() {
            System.out.println("=== Shutting Down Computer ===");
            System.out.println("Saving state...");
            System.out.println("Powering off.");
            System.out.println("=== Computer Shut Down ===\n");
        }
    }

    public static void main(String[] args) {
        // Client only interacts with the facade — no need to know about CPU, Memory, or HardDrive
        ComputerFacade computer = new ComputerFacade();

        computer.start();
        computer.shutdown();
    }
}
