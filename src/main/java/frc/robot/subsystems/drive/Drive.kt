package frc.robot.subsystems.drive

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger

class Drive : SubsystemBase("drive") {
    val io: DriveIO = if(RobotBase.isReal()){
        DriveIONeos(
            leftMotorID = 1,
            rightMotorID = 2,
            gearRatio = 1.0,
        )
    } else {
        object : DriveIO {}
    }

    val inputs = DriveIO.DriveInputs()

    val kinematics = DifferentialDriveKinematics(trackWidthMeters)

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.processInputs(this.name, inputs)
    }

    companion object {
        val trackWidthMeters = 0.5
    }
}