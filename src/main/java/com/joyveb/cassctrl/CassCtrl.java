package com.joyveb.cassctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author nivance Cassandra control tool
 * 
 */
public class CassCtrl {

	private static JSch jsch;
	private static Session session;

	private static void connect(String host, int port, String user,
			String passwd) throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, port);
		session.setPassword(passwd);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
	}

	public static boolean execCmd(String host, int port, String user,
			String passwd, String command) throws JSchException, IOException {
		BufferedReader reader = null;
		ChannelExec exec = null;
		try {
			connect(host, port, user, passwd);
			exec = (ChannelExec) session.openChannel("exec");
			exec.setInputStream(null);
			exec.setCommand(command);
			exec.connect();
			reader = new BufferedReader(new InputStreamReader(
					exec.getErrStream()));
			String buf = reader.readLine();
			if (StringUtils.isNotBlank(buf)) {
				System.out.println("host[" + host + "] exec command[ "
						+ command + " ]invoked failure，fail reason:" + buf);
				throw new JSchException(buf);
			} else {
				return true;
			}
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (exec != null) {
				exec.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		if (args.length != 5) {
			for (String str : args) {
				buffer.append(str).append(",");
			}
			System.out.println("your args is ["
					+ ((buffer.length() > 0) ? buffer.deleteCharAt(buffer
							.length() - 1) : "") + "]");
			System.err.println("Usage: host port user password command");
		} else {
			boolean flag = false;
			try {
				flag = execCmd(args[0], Integer.valueOf(args[1]), args[2],
						args[3], args[4]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			buffer.append(flag ? "success" : "failed");
			System.out.println("host[" + args[0] + "] exec command[" + args[4]
					+ "] " + buffer.toString());
		}
	}
}
