package examenPrimerTrimestreJonatanTajada;

/**
 * Ejercicio Manejo de transacciones
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio5 {

	// Credenciales de conexion
	private static final String URL = "jdbc:mysql://localhost:3306/banco";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {

		int cuentaOrigen = 1; // ID de Cuenta A
		int cuentaDestino = 2; // ID de Cuenta B
		double cantidad = 200.00; // Cantidad a transferir

		realizarTransferencia(cuentaOrigen, cuentaDestino, cantidad);
	}

	public static void realizarTransferencia(int cuentaOrigen, int cuentaDestino, double cantidad) {

		// Sentencias SQL 
		String sqlUpdateCtaA = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ?";
		String sqlUpdateCtaB = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";

		// Establecer conexion con la base de datos
		try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {

			// Desactivar el autocommit
			conexion.setAutoCommit(false);

			try (PreparedStatement psCtaA = conexion.prepareStatement(sqlUpdateCtaA);
				 PreparedStatement psCtaB = conexion.prepareStatement(sqlUpdateCtaB)) {
				
				// Actualizar cuenta de origen: quitar saldo
				psCtaA.setDouble(1, cantidad);
				psCtaA.setInt(2, cuentaOrigen);
				
				int filasAfectadasCtaA = psCtaA.executeUpdate();
				
				if (filasAfectadasCtaA == 0) {
					throw new SQLException("No se encontro la cuenta de origen o saldo insuficiente.");
				}

				// Actualizar cuenta de destino: añadir saldo
				psCtaB.setDouble(1, cantidad);
				psCtaB.setInt(2, cuentaDestino);
				
				int filasAfectadasCtaB = psCtaB.executeUpdate();
				if (filasAfectadasCtaB == 0) {
					throw new SQLException("No se encontro la cuenta de destino.");
				}

				// Confirmar la transaccion
				conexion.commit();
				System.out.println("Transferencia realizada con exito.");

			} catch (SQLException e) {
				
				// En caso de error, deshacer la transaccion
				System.err.println("Error durante la transferencia: " + e.getMessage());
				conexion.rollback();
				System.out.println("Transaccion revertida.");
				
			} finally {
				// Volver a activar 
				conexion.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos: " + e.getMessage());
		}
	}
}
