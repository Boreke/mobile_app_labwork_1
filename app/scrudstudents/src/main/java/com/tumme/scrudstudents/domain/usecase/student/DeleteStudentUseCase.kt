package com.tumme.scrudstudents.domain.usecase.student

import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.StudentRepository

class DeleteStudentUseCase(private val repo: StudentRepository) {
    suspend operator fun invoke(student: UserEntity) = repo.deleteStudent(student)
}
