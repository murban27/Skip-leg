import { colors } from './colors';

export const typography = {
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    lineHeight: 32,
    color: colors.textPrimary,
  },
  heading: {
    fontSize: 20,
    fontWeight: 'bold',
    lineHeight: 22,
    color: colors.textPrimary,
  },
  body: {
    fontSize: 15,
    fontWeight: 'normal',
    lineHeight: 16,
    color: colors.textPrimary,
  },
  caption: {
    fontSize: 12,
    fontWeight: 'normal',
    lineHeight: 13,
    color: colors.textPrimary,
  },
  numeric: {
    fontSize: 16,
    fontWeight: 'normal',
    lineHeight: 18,
    color: colors.textPrimary,
    fontVariant: ['tabular-nums'],
  },
} as const;