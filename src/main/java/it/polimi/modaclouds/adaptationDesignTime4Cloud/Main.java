package it.polimi.modaclouds.adaptationDesignTime4Cloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@Parameter(names = { "-h", "--help", "-help" }, help = true, description = "Shows this help and exits")
	private boolean help;

	@Parameter(names = "-dbConnectionFile", description = "Path to the connection file", required = true)
	private String dbConnectionFile = null;
	
	@Parameter(names = "-basePath", description = "Base path where the files will be searched from and produced in", required = true)
	private String basePath = null;

	@Parameter(names = "-space4cloudSolutionPath", description = "Path to the solution of Space 4Clouds-Dev")
	private String space4cloudSolutionPath = null;
	
	@Parameter(names = "-functionalityToTierPath", description = "Path to the functionality2tier file")
	private String functionalityToTierPath = null;
	
	@Parameter(names = "-space4cloudPerformancePath", description = "Path to the performance results of Space 4Clouds-Dev")
	private String space4cloudPerformancePath = null;
	
	@Parameter(names = "-optimizationWindowLenght", description = "Length of the window to be considered")
	private int optimizationWindowLenght = AdaptationModelBuilder.DEFAULT_OPTIMIZATION_WINDOW_LENGTH;
	
	@Parameter(names = "-timestepDuration", description = "Timestep duration to be considered")
	private int timestepDuration = AdaptationModelBuilder.DEFAULT_TIMESTEP_DURATION;
	
	@Parameter(names = "-suffix", description = "Suffix for the files, usually for the provider name of the solution", required = true)
	private String suffix = null;
	
	@Parameter(names = "-demands", description = "Demands of the functionalities, in the format <id>:<demand> (more can be expressed, just put a space between them)")
	private List<String> demands = new ArrayList<String>();

	public static final String APP_TITLE = "\nDesign2Runtime Connector\n";

	static {
		// Optionally remove existing handlers attached to j.u.l root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
		// the initialization phase of your application
		SLF4JBridgeHandler.install();
	}

	public static void main(String[] args) {
		args = "-basePath /Users/ft/Desktop/tmp/trash/space4cloud -suffix Amazon -dbConnectionFile /Users/ft/Development/workspace-s4c-runtime/modaclouds-models/HTTPAgent/localDBConnection.properties -functionalityToTierPath /Users/ft/Development/workspace-s4c-runtime/modaclouds-models/HTTPAgent/functionalityChain2Tier.xml -demands _sPjOMNhwEeSGjb1hTo3-zQ:30.0".split(" ");

		Main m = new Main();
		JCommander jc = new JCommander(m, args);

		System.out.println(APP_TITLE);

		if (m.help) {
			jc.usage();
			System.exit(0);
		}
		
		Map<String, Double> map = new HashMap<String, Double>();
		for (String s : m.demands) {
			String[] split = s.split(":");
			try {
				map.put(split[0], Double.parseDouble(split[1]));
			} catch (Exception e) { }
		}
		
		try {
			perform(m.dbConnectionFile, m.basePath, m.space4cloudSolutionPath, m.functionalityToTierPath, m.space4cloudPerformancePath, m.optimizationWindowLenght, m.timestepDuration, m.suffix, map);
			logger.info("Files produced correctly.");
		} catch (Exception e) {
			logger.error("Error while performing the conversion.", e);
		}
		
	}
	
	public static void perform(String dbConnectionFile, String basePath, String space4cloudSolutionPath, String functionalityToTierPath, 
			String space4cloudPerformancePath, int optimizationWindowLenght, int timestepDuration, String suffix,  Map<String, Double> functionalityDemands) throws Exception {
		AdaptationModelBuilder amb = new AdaptationModelBuilder(dbConnectionFile);
		
		amb.createAdaptationModelAndRules(basePath, space4cloudSolutionPath, functionalityToTierPath, space4cloudPerformancePath, optimizationWindowLenght, timestepDuration, suffix, functionalityDemands);
	}

}
