# Tensquare 详细功能清单

## 核心服务

### 用户服务 (tensquare_user)

- [x] 用户认证
  - [x] 手机号注册/登录
  - [x] 密码找回
  - [x] JWT 令牌管理
- [x] 用户管理
  - [x] 个人资料维护
  - [x] 权限控制(RBAC)
  - [x] 用户行为日志

### 招聘服务 (tensquare_recruit) ★★★

- [x] 职位管理
  - [x] 职位发布/下架
  - [x] 职位搜索
  - [x] 薪资范围筛选
- [ ] 简历管理
  - [x] 简历上传
  - [ ] 简历智能解析
  - [ ] 简历评分

## 社交服务

### 文章服务 (tensquare_article) ★★

- [x] 内容管理
  - [x] 富文本编辑
  - [x] 文章分类
  - [x] 审核流程
- [x] 推荐系统
  - [x] 热门文章
  - [x] 个性化推荐

### 问答服务 (tensquare_qa)

- [x] 问题管理
  - [x] 提问/回答
  - [x] 采纳最佳答案
- [ ] 付费咨询
  - [ ] 知识付费
  - [ ] 在线支付

### 活动服务 (tensquare_gathering) ★★

- [x] 活动管理
  - [x] 活动发布/取消
  - [x] 活动分类
  - [x] 活动审核
- [x] 报名管理
  - [x] 在线报名
  - [x] 报名审核
  - [x] 活动通知

### 好友服务 (tensquare_friend) ★

- [x] 好友关系
  - [x] 添加/删除好友
  - [x] 好友分组
  - [x] 好友推荐
- [x] 消息通知
  - [x] 系统消息
  - [x] 好友动态

## 基础服务

### 短信服务 (tensquare_sms) ★★

- [x] 短信发送
  - [x] 验证码短信
  - [x] 通知短信
  - [x] 营销短信
- [x] 模板管理
  - [x] 模板审核
  - [x] 模板变量

### 配置中心 (tensquare_config)

- [x] 统一配置管理
- [x] 配置热更新
- [x] 多环境支持

### 搜索服务 (tensquare_search)

- [x] 全文检索
  - [x] 文章搜索
  - [x] 职位搜索
- [ ] 语义分析
  - [ ] 搜索建议
  - [ ] 错别字纠正

## 状态说明

- ★★★ 高优先级
- ★★ 中优先级
- ★ 低优先级
- [x] 已实现
- [ ] 待实现
