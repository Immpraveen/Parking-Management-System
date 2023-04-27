package com.kpmg.parkingreservation.resources;

/**
 * The ConstantUtils class contains constants used in the email and reservation
 * system.
 */
public class ConstantUtils {

	/**
	 * The email address used as the sender of reservation confirmation emails. This
	 * field requires a valid Gmail ID.
	 */
	public final static String fromEmail = "frmintern2023@gmail.com";

	/**
	 * The password for the email account used to send reservation confirmation
	 * emails. This field should contain the correct password for the Gmail ID
	 * specified in the fromEmail field.
	 */
	public final static String password = "ED7DA83CB73C7457A47F27AE9984D3F053F6";

	/**
	 * The SMTP host used to send emails.
	 */
	public final static String smtpHost = "smtp.elasticemail.com";

	/**
	 * The port used to send emails via SMTP.
	 */
	public final static String smtpPort = "2525";

	/**
	 * The message displayed to the user when their reservation is successfully
	 * made.
	 */
	public final static String resDone = "ThankYou, Your reservation for today has been done!";

	/**
	 * The message displayed to the user when their reservation is cancelled.
	 */
	public final static String resCancel = " Your reservation for today has been Cancelled!";

	/**
	 * The message displayed to the user when their registration is successful.
	 */
	public final static String regDone = " Successfully Registered!";
}
