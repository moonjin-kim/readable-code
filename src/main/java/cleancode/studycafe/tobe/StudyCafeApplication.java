package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.CSVStudyCafeFileHandler;
import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new CSVStudyCafeFileHandler()
        );
        studyCafePassMachine.run();
    }

}
