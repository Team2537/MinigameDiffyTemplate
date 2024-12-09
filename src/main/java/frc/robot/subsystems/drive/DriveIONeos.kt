package frc.robot.subsystems.drive

import com.revrobotics.CANSparkBase
import com.revrobotics.CANSparkLowLevel
import com.revrobotics.CANSparkMax

class DriveIONeos(
    val leftMotorID: Int,
    val rightMotorID: Int,
    val gearRatio: Double,
) : DriveIO {
    val leftMotor =
        CANSparkMax(
            leftMotorID,
            CANSparkLowLevel.MotorType.kBrushless,
        )

    val rightMotor =
        CANSparkMax(
            rightMotorID,
            CANSparkLowLevel.MotorType.kBrushless,
        )

    val leftEncoder = leftMotor.encoder.apply {
        positionConversionFactor = 1/gearRatio
        velocityConversionFactor = 1/gearRatio
    }
    val rightEncoder = rightMotor.encoder.apply {
        positionConversionFactor = 1/gearRatio
        velocityConversionFactor = 1/gearRatio
    }

    val leftPID = leftMotor.pidController.apply {
        p = 0.0
        i = 0.0
        d = 0.0
    }

    val rightPID = rightMotor.pidController.apply {
        p = 0.0
        i = 0.0
        d = 0.0
    }

    override fun updateInputs(inputs: DriveIO.DriveInputs) {
        inputs.leftSpeed = leftEncoder.velocity
        inputs.leftPosition = leftEncoder.position
        inputs.leftVoltage = leftMotor.appliedOutput * leftMotor.busVoltage

        inputs.rightSpeed = rightEncoder.velocity
        inputs.rightPosition = rightEncoder.position
        inputs.rightVoltage = rightMotor.appliedOutput * rightMotor.busVoltage
    }

    override fun setVoltage(
        leftVoltage: Double,
        rightVoltage: Double,
    ) {
        leftMotor.setVoltage(leftVoltage)
        rightMotor.setVoltage(rightVoltage)
    }

    override fun setVelocity(
        leftVelocity: Double,
        rightVelocity: Double,
    ) {
        leftPID.setReference(leftVelocity, CANSparkBase.ControlType.kVelocity)
        rightPID.setReference(rightVelocity, CANSparkBase.ControlType.kVelocity)
    }
}
