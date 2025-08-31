<template>
  <div class="forget-container">
    <el-card class="box-card">
      <h2>忘记密码</h2>
      <el-form :model="form" @submit.prevent="handleForget">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input type="password" v-model="form.newPassword" placeholder="请输入新密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleForget">确认修改</el-button>
          <el-button type="text" @click="$router.push('/login')">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { forgetPassword } from '@/api/user'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()

const form = reactive({
  username: '',
  newPassword: ''
})

const handleForget = async () => {
  try {
    await forgetPassword(form)
    ElMessage.success('密码修改成功，请重新登录')
    router.push('/login') 
  } catch (e) {
    console.log(e)
    ElMessage.error('修改失败')
  }
}
</script>

<style scoped>
.forget-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.box-card {
  width: 400px;
  padding: 20px;
}
</style>
