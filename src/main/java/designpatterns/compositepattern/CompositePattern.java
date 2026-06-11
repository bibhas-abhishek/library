// Composite Design Pattern

package designpatterns.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {

    // Component interface: common contract for both leaf and composite nodes
    interface FileSystemItem {
        String name();

        int size();

        void display(String indent);
    }

    // Leaf: represents an individual object with no children
    static class File implements FileSystemItem {
        private final String name;
        private final int size;

        File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void display(String indent) {
            System.out.println(indent + "📄 " + name + " (" + size + " KB)");
        }
    }

    // Composite: contains children (both leaves and other composites),
    // delegates operations recursively to build a tree structure
    static class Directory implements FileSystemItem {
        private final String name;
        private final List<FileSystemItem> children = new ArrayList<>();

        Directory(String name) {
            this.name = name;
        }

        void add(FileSystemItem item) {
            children.add(item);
        }

        @Override
        public String name() {
            return name;
        }

        // Total size is the sum of all children — works recursively for nested directories
        @Override
        public int size() {
            int total = 0;
            for (FileSystemItem child : children) {
                total += child.size();
            }
            return total;
        }

        @Override
        public void display(String indent) {
            System.out.println(indent + "📁 " + name + " (" + size() + " KB)");
            for (FileSystemItem child : children) {
                child.display(indent + "  ");
            }
        }
    }

    public static void main(String[] args) {
        // Build a file system tree
        Directory root = new Directory("root");

        // Leaves at root level
        root.add(new File("README.md", 5));
        root.add(new File("pom.xml", 3));

        // Nested composite — treated the same as a leaf by the parent
        Directory src = new Directory("src");
        src.add(new File("Main.java", 10));
        src.add(new File("Utils.java", 8));

        Directory test = new Directory("test");
        test.add(new File("MainTest.java", 6));

        root.add(src);
        root.add(test);

        // display() works uniformly — client doesn't know if it's a file or directory
        root.display("");
    }
}
