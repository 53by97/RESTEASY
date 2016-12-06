package com.kvvssut.controller.impl;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.kvvssut.controller.RestController;

public class RestControllerImpl implements RestController {

	private static Logger logger = Logger.getLogger(RestControllerImpl.class);

	@PersistenceContext(unitName = "restapplication_persistence", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Override
	public void updateBalanceByUserName(String userName, BigDecimal amount) {
		try {
			Query query = entityManager
			          .createQuery("update RestData rd set rd.totalBalance = rd.totalBalance - :AMT where rd.userName = :UNAME");
			query.setParameter("AMT", amount);
			query.setParameter("UNAME", userName);
			logger.info("No. of rows updated: " + query.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BigDecimal getBalanceByUserName(String userName) {
		BigDecimal amount = new BigDecimal(0);
		try {
			Query query = entityManager
			          .createQuery("select rd.totalBalance from RestData rd where rd.userName = :UNAME");
			query.setParameter("UNAME", userName);
			amount = (BigDecimal) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

}
