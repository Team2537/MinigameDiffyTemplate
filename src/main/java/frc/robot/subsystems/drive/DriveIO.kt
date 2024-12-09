package frc.robot.subsystems.drive

import org.littletonrobotics.junction.LogTable
import org.littletonrobotics.junction.inputs.LoggableInputs

interface DriveIO {
    class DriveInputs : LoggableInputs {
        var leftSpeed: Double = 0.0
        var leftPosition: Double = 0.0
        var leftVoltage: Double = 0.0

        var rightSpeed: Double = 0.0
        var rightPosition: Double = 0.0
        var rightVoltage: Double = 0.0

        override fun toLog(table: LogTable) {
            table.put("leftSpeed", leftSpeed)
            table.put("leftPosition", leftPosition)
            table.put("leftVoltage", leftVoltage)

            table.put("rightSpeed", rightSpeed)
            table.put("rightPosition", rightPosition)
            table.put("rightVoltage", rightVoltage)
        }

        override fun fromLog(table: LogTable) {
            leftSpeed = table.get("leftSpeed", 0.0)
            leftPosition = table.get("leftPosition", 0.0)
            leftVoltage = table.get("leftVoltage", 0.0)

            rightSpeed = table.get("rightSpeed", 0.0)
            rightPosition = table.get("rightPosition", 0.0)
            rightVoltage = table.get("rightVoltage", 0.0)
        }
    }

    fun updateInputs(inputs: DriveInputs) {}

    fun setVoltage(
        leftVoltage: Double,
        rightVoltage: Double,
    ) {}

    fun setVelocity(
        leftVelocity: Double,
        rightVelocity: Double,
    ) {}
}
