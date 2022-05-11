package com.mueeee.clipicocli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.jansi.graalvm.AnsiConsole;

import java.io.File;

@Command(name = "cli-picocli", mixinStandardHelpOptions = true, version = "CliPicocli 1.0")
public class CliPicocliApp implements Runnable {

    // 详细模式
    @Option(names = {"-v", "--verbose"}, description = "Verbose mode. Helpful for troubleshooting.")
    private boolean verbose = false;

    // 文件处理方式
    @Option(names = {"-t", "--type"}, description = "File process mode", defaultValue = "1", showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private String type = "";

    // 多个文件参数
    @Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to process.")
    private File[] inputFiles;

    public void run() {
        if (verbose) {
            System.out.println(inputFiles.length + " files to process...");
        }
        if (type != null) {
            System.out.println("File processing mode: " + type);
        }
        for (File f : inputFiles) {
            System.out.println(f.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling user
        // requests for usage help or version help can be done with one line of code.

        // int exitCode = new CommandLine(new CliPicocliApp()).execute(args);
        // System.exit(exitCode);

        int exitCode = 0;
        // 在 Windows 上启用颜色
        try (AnsiConsole ansi = AnsiConsole.windowsInstall()) {
            exitCode = new CommandLine(new CliPicocliApp()).execute(args);
        }
        System.exit(exitCode);
    }
}
