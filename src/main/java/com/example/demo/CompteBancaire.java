package com.example.demo;

public class CompteBancaire {


	private double solde;
	private NotificationService notificationService;
	public CompteBancaire(double soldeInitial, NotificationService notificationService) {
	if (soldeInitial < 0) {
	throw new IllegalArgumentException("Le solde initial ne peut pas être négatif.");
	}
	this.solde = soldeInitial;
	this.notificationService = notificationService;
	}
	public double getSolde() {return solde;
	}
	public void deposer(double montant) {
	if (montant <= 0) {
	throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
	}
	solde += montant;
	notificationService.envoyerNotification("Dépôt de " + montant + " effectué.");
	}
	public void retirer(double montant) {
	if (montant <= 0) {
	throw new IllegalArgumentException("Le montant du retrait doit être positif.");
	}
	if (montant > solde) {
	throw new IllegalArgumentException("Fonds insuffisants.");
	}
	solde -= montant;
	notificationService.envoyerNotification("Retrait de " + montant + " effectué.");
	}
	}