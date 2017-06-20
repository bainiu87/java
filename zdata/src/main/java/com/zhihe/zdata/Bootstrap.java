package com.zhihe.zdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring boot入口方法<br/>
 * Created by shipeng.hou
 */
@EnableScheduling
@SpringBootApplication
//@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class,ThymeleafAutoConfiguration.class})
public class Bootstrap extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	/**
	 *
	 * @param args
	 *            1、port
	 */
	public static void main(String[] args) throws Exception {
		doStart(args);
	}

	/**
	 * 开始启动
	 * @param args
	 * @throws Exception
	 */
	public static void doStart(String[] args) throws Exception {
		// 处理参数
		argsHandle(args);

		// 获取当前线程的PID
		getPID();

		// 启动spring boot
		SpringApplication.run(Bootstrap.class, args);

		// 如果执行了kill -15 pid ，则会执行该钩子
		closeHook();

		// 在交互模式下读取用户输入
		//readInput();
	}

	/**
	 * 获取当前程序的PID
	 *
	 * @throws Exception
	 */
	private static void getPID() throws Exception {
		// get name representing the running Java virtual machine.
		String name = ManagementFactory.getRuntimeMXBean().getName();
		// get pid
		final String pid = name.split("@")[0];
		System.out.println("Pid is:" + pid);

		// 获取上下文路径
		URL url = Bootstrap.class.getResource("");
		System.out.println("cotextPath:" + url);
		if (url != null) {
			String cotextPath = url.getPath();
			if (cotextPath.contains(".jar!")) {// 说明是jar包
				cotextPath = cotextPath.substring(0, cotextPath.indexOf("!"));
				cotextPath = cotextPath.substring(0,
						cotextPath.lastIndexOf("/"));
			} else {// 不然从线程里得
				url = Thread.currentThread().getContextClassLoader()
						.getResource("");
				if (url != null)
					cotextPath = url.getPath();
				else
					throw new Exception("无法生成PID文件");
			}
			if (cotextPath.contains("file:")) {// 针对windows的情况
				cotextPath = cotextPath.replace("file:", "");
			}

			// String cotextPath = "/var/run/";
			System.out.println("cotextPath:" + cotextPath);

			// 写PID文件
			FileUtils.writeStringToFile(new File(cotextPath + "/zdata.pid"), pid,"UTF-8");
		}
	}

	/**
	 * 处理参数
	 *
	 * @param args
	 *            用户入参
	 */
	private static void argsHandle(String[] args) {
		if (args != null) {
			if (args.length >= 1) {// 第一个参数为端口号
				String sPort = args[0];
				System.out.println("port='" + sPort + "'");
				try {
					port = Integer.valueOf(sPort);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 钩子
	 */
	private static void closeHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("jvm is closing...");
			}
		});
	}

	/**
	 * 读取用户输入
	 *
	 * @throws IOException
	 */
	private static void readInput() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)); // 输出
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equalsIgnoreCase("exit")) {
				System.exit(0);
			} else {
				System.out.println(line);
			}
		}
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Bootstrap.class);
	}

	public void customize(
			ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		if (port > 0)// 如果存在端口号，则使用该端口
			configurableEmbeddedServletContainer.setPort(port);
	}

	private static int port;// 端口号


}
