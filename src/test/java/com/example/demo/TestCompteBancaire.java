package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;





public class TestCompteBancaire {
	
	
	    private CompteBancaire compte;
	    private NotificationService notificationServiceMock;

	    @BeforeEach
	    void setUp() {
	        notificationServiceMock = mock(NotificationService.class);
	        compte = new CompteBancaire(100, notificationServiceMock);
	    }

	    @Test
	    void testDepotAvecNotification() {
	        compte.deposer(50);
	        assertEquals(150, compte.getSolde());
	        verify(notificationServiceMock).envoyerNotification("Dépôt de 50.0 effectué.");
	    }

	    @Test
	    void testRetraitAvecNotification() {
	        compte.retirer(30);
	        assertEquals(70, compte.getSolde());
	        verify(notificationServiceMock).envoyerNotification("Retrait de 30.0 effectué.");
	    }

	    @Test
	    void testRetraitDepassantSolde() {
	        assertThrows(IllegalArgumentException.class, () -> compte.retirer(200));
	        verify(notificationServiceMock, never()).envoyerNotification(anyString());
	    }

	    @Test
	    void testVerificationNombreAppels() {
	        compte.deposer(10);
	        compte.deposer(20);
	        verify(notificationServiceMock, times(2)).envoyerNotification(anyString());
	    }
	}

