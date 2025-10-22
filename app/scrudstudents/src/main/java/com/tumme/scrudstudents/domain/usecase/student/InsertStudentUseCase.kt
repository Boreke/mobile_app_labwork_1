package com.tumme.scrudstudents.domain.usecase.student

import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.StudentRepository

class InsertStudentUseCase(private val repo: StudentRepository) {
    suspend operator fun invoke(student: UserEntity) = repo.insertStudent(student)
}
