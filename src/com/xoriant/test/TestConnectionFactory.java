package com.xoriant.test;

import com.xoriant.dao.supp.ConnectionFactory;

public class TestConnectionFactory {
	public static void main(String[] args) {
		System.out.println(ConnectionFactory.getConnection());
	}
}
